package com.supportcom.ocp.managedbean;



import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.supportcom.ocp.service.LoginServiceImpl;
import com.supportcomm.ocp.entity.Company;
import com.supportcomm.ocp.entity.Login;
import com.supportcomm.ocp.util.Telas;




@ManagedBean(name="loginBean")
@ViewScoped
public class LoginManagedBean {

	  
	   LoginServiceImpl loginService;
	   
	   


	    private static Logger log = Logger.getLogger(LoginManagedBean.class);
	    private static final String SUCCESS = "success";
	    private static final String ERROR   = "error";
	    
	    private Long loginId=0l;
	    private String email="";
	    private String password="";
	    private String message;
	    private String userName="";
	    private String description="";
	   
	    private Company company= new Company();
	    private Login login;
	    private Boolean edita = new Boolean(Boolean.FALSE);
	    
	    String userLevel="";
	    
	    public LoginManagedBean(){
	    	this.loginId    =0l;
	    	this.email     = "";
	    	this.password  = "";
	    	this.userName  = "";
	    	this.userLevel = "";
	    	this.description="";
	    	this.company= new Company();
		    Login login = new Login();	
	    }
	    
	    
	    public String login() {
	        try {
	    	Login login = new Login();
	    	loginService =  new LoginServiceImpl();
	    	login = loginService.getUserAccessLoginPass(email, password);
	    	
             if (login!=null){
                 
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("logado", login);  
                this.userName =login.getUsername();
             
             }
	    } catch (Exception ex) {
            Logger.getLogger(LoginManagedBean.class.getName()).log(Level.ERROR, null, ex);
        }
	        return "index";
	        
	    }

	    public String logout(){
	    	 try {
	    	        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("logado");
	    	        FacesContext.getCurrentInstance().getExternalContext().redirect(Telas.INDEX.getValor());
	    	          } catch (IOException ex) {
	    	           Logger.getLogger(LoginManagedBean.class.getName()).log(Level.ERROR, null, ex);
	    	        }
	    	  return "index";

	    }
	    
	    
	    public Login edit(Login login){
	    	this.company = login.getCompany();
	    	this.userName = login.getUsername();
	    	this.description = login.getDescription();
	    	this.email = login.getEmail();
	    	this.userLevel = login.getUserLevel();
	    	this.loginId = login.getLoginId();
	    	return login;
	    }
	    
	    public void remove(Login login){
	    	loginService =  new LoginServiceImpl();
	    	loginService.delete(login);
	    	
	    }
	    
	    public String save(){
	    	LoginServiceImpl loginService =  new LoginServiceImpl();
	    	
	    	
	    	Login login = new Login();
	    	login.setEmail(email);
	    	login.setPassword("");
	    	login.setUsername(userName);
	    	login.setUserLevel(userLevel);
	    	login.setDescription(description);
	    	
	    	login.setCompany(company);
	    	login.setLoginId(loginId);
	    	
	    	
	    	
	    	
	    	
	    	if(loginId>0){
	    		loginService.update(login);
	    	}else{
	    		loginService.save(login);
	    	}
	    		
	    	
	    	return "user";
	    }
	    
	    
	    public List<Login> getListAll(){
	    	loginService =  new LoginServiceImpl();
	    	return loginService.getAll();
	    	
	    }
	    
	    
	    
	    public void reset() {
	    	this.email     = "";
	    	this.password  = "";
	    	this.userName  = "";
	    	this.userLevel = "";
	    	this.description="";
	    	this.company= new Company();
	    	this.loginId = 0l;
	    	setEdita(Boolean.FALSE);
	    }
	    
	    //----------------------------------------------------------
	    public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public String getUserLevel() {
			return userLevel;
		}


		public void setUserLevel(String userLevel) {
			this.userLevel = userLevel;
		}


		public Login getLogin() {
			return login;
		}


		public void setLogin(Login login) {
			this.login = login;
		}


		public Company getCompany() {
			return company;
		}


		public void setCompany(Company company) {
			this.company = company;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public Boolean getEdita() {
			return edita;
		}


		public void setEdita(Boolean edita) {
			this.edita = edita;
		}


		public Long getLoginId() {
			return loginId;
		}


		public void setLoginId(Long loginId) {
			this.loginId = loginId;
		}

	
}
