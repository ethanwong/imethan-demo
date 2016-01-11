package cn.imethan.web.struts2;
import com.opensymphony.xwork2.ActionSupport;
/**
 * HelloWorldAction.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月31日上午9:39:42
 */ 
public class HelloWorldAction extends ActionSupport { 
    private static final long serialVersionUID = 1L; 
    private MessageStore messageStore;
    private static int helloCount = 0;
    
    private String userName;
    
    public String getUserName() {
        return userName;
    }
     
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public int getHelloCount() {
        return helloCount;
    }
     
    public void setHelloCount(int helloCount) {
        HelloWorldAction.helloCount = helloCount;
    }

    public String execute() throws Exception {         
        messageStore = new MessageStore() ;
        helloCount++;
        return SUCCESS;
    } 
    public MessageStore getMessageStore() {
        return messageStore;
    } 
    public void setMessageStore(MessageStore messageStore) {
        this.messageStore = messageStore;
    } 
}

