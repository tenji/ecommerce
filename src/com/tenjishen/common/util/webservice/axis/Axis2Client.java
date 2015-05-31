package com.tenjishen.common.util.webservice.axis;

public class Axis2Client {
    private Axis2Client() {

    }

    
    /** RPC 客户端 **/
    public static String sendServiceByRPC(String url, String nameSpace,
            String method, String xmlStr) {
    	/*
        try {
            RPCServiceClient serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();
            options.setProperty(HTTPConstants.CHUNKED, false);
            // options.setTimeOutInMilliSeconds(10000);// 超时设置

            EndpointReference targetEPR = new EndpointReference(url);
            options.setTo(targetEPR);

            // 添加头部
            Platform platForm = Context.getInstance().getPlatform();
            serviceClient
                    .addHeader(HeaderOMElement.createHeaderOMElement(nameSpace,
                            (StringUtils.isBlank(xmlStr) ? "" : xmlStr)
                                    + platForm.getAppendText(),
                            platForm.getHeaderKey()));
            QName opAddEntry = new QName(nameSpace, method);
            // 如果被调用的WebService方法没有返回值，应使用RPCServiceClient类的invokeRobust方法，
            String result = (String) serviceClient.invokeBlocking(opAddEntry,
                    new Object[] { xmlStr }, new Class[] { String.class })[0];
            return result;
        } catch (AxisFault e) {
            e.printStackTrace();
        }
        */
        return null;
    }

    /** OME 客户端 **/
    public static String sendServiceByOME(String wsURL, String actionName,
            String nameSpace, String method, String[] params, String[] values) {
    	/*
        ServiceClient sender = null;
        try {
            Options options = new Options();
            options.setTo(new EndpointReference(wsURL));
            sender = new ServiceClient();
            sender.setOptions(options);
            options.setAction(actionName);
            OMFactory fac = OMAbstractFactory.getOMFactory();
            OMElement result = sender.sendReceive(getMethodOMElement(fac,
                    nameSpace, "tns", method, params, values));
            String rs = result.getFirstElement().getText();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                sender.cleanupTransport();
                sender.cleanup();
            } catch (AxisFault e) {
                e.printStackTrace();
            }
        }
        */
        return null;
    }

    /* 绑定参数对应的参数名 */
    /*
    private static OMElement getMethodOMElement(OMFactory fac,
            String nameSpace, String tns, String methodName, String[] args,
            String[] vals) {
        OMNamespace omNs = fac.createOMNamespace(nameSpace, tns);
        OMElement method = fac.createOMElement(methodName, omNs);
        for (int i = 0; i < args.length; i++) {
            // 新建参数OMElement对象实例，设置参数名
            OMElement params = fac.createOMElement(args[i], omNs);
            // 给参数名对象设置参数值
            params.setText(vals[i]);
            // 将参数OMElement对象添加到Method对象中
            method.addChild(params);
        }
        // 返回request的方法 SOAP包
        return method;
    }
    */

}
