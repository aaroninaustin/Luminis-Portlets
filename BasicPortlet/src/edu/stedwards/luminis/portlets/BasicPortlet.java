package edu.stedwards.luminis.portlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;




public class BasicPortlet extends GenericPortlet{

	
	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
				PrintWriter out = response.getWriter();
				out.println("<strong>Hello World!</strong><br />This is awesome.");
				
	}
}
