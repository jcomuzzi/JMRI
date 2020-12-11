package jmri.jmrit.logixng.actions.configurexml;

import jmri.InstanceManager;
import jmri.jmrit.logixng.DigitalActionManager;
import jmri.jmrit.logixng.actions.For;

import org.jdom2.Attribute;
import org.jdom2.Element;

import jmri.jmrit.logixng.MaleSocket;

/**
 * Handle XML configuration for For objects.
 *
 * @author Bob Jacobsen Copyright: Copyright (c) 2004, 2008, 2010
 * @author Daniel Bergqvist Copyright (C) 2020
 */
public class ForXml extends jmri.managers.configurexml.AbstractNamedBeanManagerConfigXML {

    /**
     * Default implementation for storing the contents of a For
     *
     * @param o Object to store, of type For
     * @return Element containing the complete info
     */
    @Override
    public Element store(Object o) {
        For p = (For) o;

        Element element = new Element("for");
        element.setAttribute("class", this.getClass().getName());
        element.addContent(new Element("systemName").addContent(p.getSystemName()));
        
        storeCommon(p, element);

        Element e2 = new Element("initSocket");
        e2.addContent(new Element("socketName").addContent(p.getChild(1).getName()));
        MaleSocket socket = p.getThenActionSocket().getConnectedSocket();
        String socketSystemName;
        if (socket != null) {
            socketSystemName = socket.getSystemName();
        } else {
            socketSystemName = p.getInitActionSocketSystemName();
        }
        if (socketSystemName != null) {
            e2.addContent(new Element("systemName").addContent(socketSystemName));
        }
        element.addContent(e2);

        e2 = new Element("whileSocket");
        e2.addContent(new Element("socketName").addContent(p.getChild(0).getName()));
        socket = p.getWhileExpressionSocket().getConnectedSocket();
        if (socket != null) {
            socketSystemName = socket.getSystemName();
        } else {
            socketSystemName = p.getWhileExpressionSocketSystemName();
        }
        if (socketSystemName != null) {
            e2.addContent(new Element("systemName").addContent(socketSystemName));
        }
        element.addContent(e2);

        e2 = new Element("afterEachSocket");
        e2.addContent(new Element("socketName").addContent(p.getChild(2).getName()));
        socket = p.getAfterEachActionSocket().getConnectedSocket();
        if (socket != null) {
            socketSystemName = socket.getSystemName();
        } else {
            socketSystemName = p.getAfterEachExpressionSocketSystemName();
        }
        if (socketSystemName != null) {
            e2.addContent(new Element("systemName").addContent(socketSystemName));
        }
        element.addContent(e2);

        e2 = new Element("doSocket");
        e2.addContent(new Element("socketName").addContent(p.getChild(2).getName()));
        socket = p.getDoActionSocket().getConnectedSocket();
        if (socket != null) {
            socketSystemName = socket.getSystemName();
        } else {
            socketSystemName = p.getDoExpressionSocketSystemName();
        }
        if (socketSystemName != null) {
            e2.addContent(new Element("systemName").addContent(socketSystemName));
        }
        element.addContent(e2);

        return element;
    }
    
    @Override
    public boolean load(Element shared, Element perNode) {
        
        String sys = getSystemName(shared);
        String uname = getUserName(shared);
        For h = new For(sys, uname);

        loadCommon(h, shared);
        
        Element socketName = shared.getChild("whileSocket").getChild("socketName");
        h.getChild(0).setName(socketName.getTextTrim());
        Element socketSystemName = shared.getChild("whileSocket").getChild("systemName");
        if (socketSystemName != null) {
            h.setWhileExpressionSocketSystemName(socketSystemName.getTextTrim());
        }
        
        socketName = shared.getChild("initSocket").getChild("socketName");
        h.getChild(1).setName(socketName.getTextTrim());
        socketSystemName = shared.getChild("initSocket").getChild("systemName");
        if (socketSystemName != null) {
            h.setInitActionSocketSystemName(socketSystemName.getTextTrim());
        }
        
        socketName = shared.getChild("afterEachSocket").getChild("socketName");
        h.getChild(2).setName(socketName.getTextTrim());
        socketSystemName = shared.getChild("afterEachSocket").getChild("systemName");
        if (socketSystemName != null) {
            h.setAfterEachActionSocketSystemName(socketSystemName.getTextTrim());
        }
        
        socketName = shared.getChild("doSocket").getChild("socketName");
        h.getChild(2).setName(socketName.getTextTrim());
        socketSystemName = shared.getChild("doSocket").getChild("systemName");
        if (socketSystemName != null) {
            h.setDoActionSocketSystemName(socketSystemName.getTextTrim());
        }
        
        InstanceManager.getDefault(DigitalActionManager.class).registerAction(h);
        return true;
    }
    
//    private final static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ForXml.class);
}
