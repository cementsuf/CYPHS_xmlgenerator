package uk.gov.hscic.xmlgenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Generate {

	public static void main(String[] args) {

		System.out.println("--- CYPHS XML Generator tool ---");
		Properties propConnection = new Properties();
		InputStream inputConnection = null;

		try {
			inputConnection = new FileInputStream("config.properties");
			propConnection.load(inputConnection);
		} catch (FileNotFoundException e1) {
			System.out.print("Properties file not found!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		

		try {
			Class.forName(propConnection.getProperty("connection.driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find driver!");
			e.printStackTrace();
			return;
		}

		System.out.println("Database driver registered!");

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(propConnection.getProperty("connection.connectionstring"),
					propConnection.getProperty("connection.username"),
					propConnection.getProperty("connection.password"));
		} catch (SQLException e) {

			System.out.println("Connection Failed!");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("Connection successful!");
		} else {
			System.out.println("Failed to make connection!");
		}

		buildXML(connection);

	}

	private static Document buildXML(Connection connection) {

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");
			prop.load(input);

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docFactory.setNamespaceAware(true);
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element ns = doc.createElementNS("http://www.datadictionary.nhs.uk/messages/CYPHS-v1-5", "CYPHS:CYPHS");
			ns.setAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema-instance");
			doc.appendChild(ns);

			Element firstElement = doc.createElement("CYP000");
			ns.appendChild(firstElement);

			// organisation details
			Element C000010 = doc.createElement("C000010");
			C000010.setTextContent("1");
			Element C000020 = doc.createElement("C000020");
			C000020.setTextContent(prop.getProperty("OrgCodeProvider"));
			Element C000030 = doc.createElement("C000030");
			C000030.setTextContent(prop.getProperty("OrgCodeSubmitter"));
			Element C000040 = doc.createElement("C000040");
			C000040.setTextContent(prop.getProperty("startdate"));
			Element C000050 = doc.createElement("C000050");
			C000050.setTextContent(prop.getProperty("enddate"));
			Element C000060 = doc.createElement("C000060");
			C000060.setTextContent(Objects.toString(System.currentTimeMillis()));
			Element C000070 = doc.createElement("C000070");
			C000070.setTextContent("rr");

			firstElement.appendChild(C000010);
			firstElement.appendChild(C000020);
			firstElement.appendChild(C000030);
			firstElement.appendChild(C000040);
			firstElement.appendChild(C000050);
			firstElement.appendChild(C000060);
			firstElement.appendChild(C000070);

			BuildElements.createElements(connection, doc, firstElement);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(prop.getProperty("output.location") + "xmloutput.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);
			
			System.out.println("Done!");
			
		} catch (SQLException | TransformerException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
