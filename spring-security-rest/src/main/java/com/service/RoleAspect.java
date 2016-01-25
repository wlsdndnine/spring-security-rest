package com.service;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.PathVariableMapMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by we on 2016. 1. 13..
 */
@Aspect
@Component
@Setter
@Getter
public class RoleAspect {

    @Autowired
    private RoleService roleService;

    @Autowired
    private HttpServletRequest request;

    private String apiPath = "";
    private String domainPath = null;


    @Around("@annotation(com.service.Role)")
    public Object pointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        String apiName = "";
        DomainType domainType = null;
        Object result = null;

        try {
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            Method method = signature.getMethod();
            apiName = method.getDeclaredAnnotation(Role.class).apiName();
            domainType =  method.getDeclaredAnnotation(Role.class).DomainType();
            setPathInfo();
        } catch (Exception e){
            throw new RuntimeException();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("apiPath : " + apiPath);
        System.out.println("domainPath : " + domainPath);

        System.out.println("apiName : " + apiName);
        System.out.println("domainType : " + domainType.name());
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "error page!");
//        throw new ModelAndViewDefiningException(mav);
        try {
            result =  (Object) joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }


    private void setPathInfo() {
        Map<String,String> decodedUriVariables = null;
        decodedUriVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        setDomainPath(decodedUriVariables.get("domain"));
        setApiPath(decodedUriVariables.get("apiPath"));
    }
/*
    @Before("pointCut()")
    public void beforeTargetMethod(JoinPoint joinPoint){
        Class clazz = joinPoint.getTarget().getClass();
        boolean checkRole = roleService.checkRole((DomainType) joinPoint.getArgs()[0], (String) joinPoint.getArgs()[1]);
    }

   public Object RemoveTkn(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest req = null;
        ModelAndView mav = new ModelAndView();
        for (Object obj : joinPoint.getArgs()) {
            if (obj instanceof HttpServletRequest) {
                req = (HttpServletRequest) obj;
            }
        }

        if (!TokenMngUtil.isTokenValid(req)) {
            String referrer = req.getHeader("referrer");
            System.out.println(referrer + "잘못된 접근입니다. 중복방지 Token error");
            mav.addObject("error", "잘못된 접근 입니다.");
            mav.setViewName("redirect:/login");
            return mav;
        }
    TokenMngUtil.resetToken(req);
    Object result = joinPoint.proceed();
    return result;
}
*/
}
