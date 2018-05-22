package com.tkSystem.system;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

//@Component  
public class DataSourceInterceptor {  
 
   public void setdataSourceOne(JoinPoint jp) {  
       DatabaseContextHolder.setCustomerType("dataSourceOne");  
   }  
     
   public void setdataSourceTwo(JoinPoint jp) {  
       DatabaseContextHolder.setCustomerType("dataSourceTwo");  
   }  
}  