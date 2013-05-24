package com.app.web.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class JsonDateDeserializer extends JsonDeserializer<Date>
{
	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException, JsonProcessingException
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = jsonparser.getText();
		try
		{
			return format.parse(date);
		}
		catch (ParseException e)
		{
			throw new RuntimeException(e);
		}
	}
}