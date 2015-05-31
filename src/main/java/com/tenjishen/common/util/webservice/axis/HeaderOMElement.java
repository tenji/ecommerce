package com.tenjishen.common.util.webservice.axis;

public class HeaderOMElement {
	/*
    public static OMElement createHeaderOMElement(String nameSpace,
            String signStr, String userName) {
        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace SecurityElementNamespace = factory.createOMNamespace(
                nameSpace, "wsse");
        OMElement authenticationOM = factory.createOMElement("Authentication",
                SecurityElementNamespace);
        OMElement usernameOM = factory.createOMElement("dywToken",
                SecurityElementNamespace);
        OMElement passwordOM = factory.createOMElement("dywSign",
                SecurityElementNamespace);
        usernameOM.setText(userName);
        passwordOM.setText(EncryptUitls.MD5Digest(signStr));
        authenticationOM.addChild(usernameOM);
        authenticationOM.addChild(passwordOM);
        return authenticationOM;
    }
    */
}
