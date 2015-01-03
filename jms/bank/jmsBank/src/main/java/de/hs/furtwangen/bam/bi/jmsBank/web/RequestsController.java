package de.hs.furtwangen.bam.bi.jmsBank.web;

import java.util.Properties;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hs.furtwangen.bam.bi.jmsBank.job.SendAResponseJob;
import de.hs.furtwangen.bam.bi.jmsBank.model.Request;
import de.hs.furtwangen.bam.bi.jmsBank.model.RequestQuoteRate;
import de.hs.furtwangen.bam.bi.jmsBank.service.RequestService;
import de.hs.furtwangen.bam.bi.jmsBank.utils.ValidatorUtils;

@Controller
public class RequestsController {

	@Resource(name = "configProperties")
	private Properties configProperties;

	@Autowired
	private ValidatorUtils validatorUtils;

	@Autowired
	private RequestService requestService;
	
	@Autowired
	private SendAResponseJob sendAResponseJob;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("requests", requestService.getValideRequests());
		model.addAttribute("bankName",
				configProperties.getProperty("bank.name"));
		model.addAttribute("autoresponse",
				configProperties.getProperty("bank.autorespond"));
		return "index";
	}

	@RequestMapping(value = "/invalid", method = RequestMethod.GET)
	public String invalidRequests(Model model) {

		model.addAttribute("requests", requestService.getInValideRequests());

		model.addAttribute("minTerm", validatorUtils.getMinTerm());
		model.addAttribute("maxTerm", validatorUtils.getMaxTerm());

		model.addAttribute("minAmount", validatorUtils.getMinAmount());
		model.addAttribute("maxAmount", validatorUtils.getMaxAmount());

		model.addAttribute("minConsumerRate",
				validatorUtils.getMinConsumerRate());
		model.addAttribute("maxConsumerRate",
				validatorUtils.getMaxConsumerRate());

		model.addAttribute("bankName",
				configProperties.getProperty("bank.name"));
		model.addAttribute("autoresponse",
				configProperties.getProperty("bank.autorespond"));

		return "invalid";
	}

	@RequestMapping(value = "/edit/{requestId}", method = RequestMethod.GET)
	public String edit(@PathVariable String requestId, Model model) {
		model.addAttribute("request", requestService.findOne(requestId));
		return "edit";
	}

	@RequestMapping(value = "/delete/{requestId}", method = RequestMethod.GET)
	public String delete(@PathVariable String requestId, Model model) {
		requestService.delete(requestId);
		return "redirect:/";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String saveRequest(
			@Valid @ModelAttribute("request") RequestQuoteRate request,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

		System.out.println(request.getRequestId() + " "
				+ request.getQuoteRate());

		try {
			Double quote = Double.parseDouble(request.getQuoteRate());
			Request requestDB = requestService.findOne(request.getRequestId());
			requestDB.quoteRate = quote;
			requestService.save(requestDB);
			try {
				sendAResponseJob.doJob(requestDB);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (NumberFormatException nfe) {
			System.out.println("QuoteRate was not a valid double-value!");
			redirectAttributes.addFlashAttribute("error", "QuoteRate was not a valid double-value!");
			return "redirect:/edit/"+request.getRequestId();
		}
	
		
		return "redirect:/";
	}

}
