package com.app.web.rest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.app.domain.Contact;
import com.app.services.ContactService;

//--------------------------------------------------------------------------------------------------------------------------------
@Controller
public class ContactController
{
	// --------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	private ContactService contactService;

	@Autowired
	private View jsonView_i;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Retrieves the matching Employee associated to the given key.
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/test/{key}", method = { RequestMethod.GET })
	@ResponseBody
	public ModelAndView loadWithPrimaryKey(@PathVariable String key)
	{
		Contact contact = null;
		if (!StringUtils.isNotBlank(key) || !StringUtils.isNumeric(key))
		{
			String sMessage = "Error invoking contact - Invalid key parameter";
			return createErrorResponse(sMessage);
		}
		else
		{
			try
			{
				contact = contactService.getContactById(key);
			}
			catch (Exception e)
			{
				String sMessage = "Error fetching contact with key " + key;
				return createErrorResponse(String.format(sMessage, e.toString()));
			}
		}

		ModelAndView mav = new ModelAndView(jsonView_i, DATA_FIELD, contact);
		return mav;
	}

	/**
	 * Create an error REST response.
	 * @param sMessage: the s message
	 * @return the model and view
	 */
	private ModelAndView createErrorResponse(String sMessage)
	{
		return new ModelAndView(jsonView_i, ERROR_FIELD, sMessage);
	}

	/**
	 * Injector methods.
	 * @param contactService_p: the new contact service
	 */
	public void setContactService(ContactService service)
	{
		contactService = service;
	}

	/**
	 * Injector methods.
	 * @param view: the new json view
	 */
	public void setJsonView(View view)
	{
		jsonView_i = view;
	}
}