package com.app.domain.model;

import org.junit.Assert;

import java.io.*;

//--------------------------------------------------------------------------------------------------------------------------------
/** Base class for entity tests. */
//--------------------------------------------------------------------------------------------------------------------------------

public class BaseTest
{
	public Object getSerializableClass(Class<?> cls, Object objectToSerialize)
	{
		try
		{
		    // serialize
		    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		    ObjectOutputStream oos = new ObjectOutputStream(buffer);
		    oos.writeObject(objectToSerialize);
		    oos.flush();
		    oos.close();
		
		    // deserialize
		    ByteArrayInputStream in = new ByteArrayInputStream(buffer.toByteArray());
		    ObjectInputStream ois = new ObjectInputStream(in);
		    Object obj = ois.readObject();
		
		    // verify the object is deserialized correctly
		    if (cls.isInstance(obj))
		        return obj;
		    else
		    	return null;
		}
		catch (IOException e)
		{
		    // exception in serializing object to/from stream
		    Assert.fail("Error serializing/deserializing");
		    return null;
		}
		catch (ClassNotFoundException e)
		{
		    // Unable to cast
		    Assert.fail("Cast error while serializing/deserializing");
		    return null;
		}
	}
}