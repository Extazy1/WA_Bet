package com.aspect;

import com.annotation.RequiresAuthority;
import com.exception.PermissionDeniedException;
import com.model.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@Aspect
@Component
public class AuthorityAspect {

    private final HttpServletRequest request;

    public AuthorityAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Around("@annotation(com.annotation.RequiresAuthority)")
    public Object checkAuthority(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            throw new PermissionDeniedException("用户未登录");
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequiresAuthority requiresAuthority = method.getAnnotation(RequiresAuthority.class);

        if (user.getAuthority() < requiresAuthority.value()) {
            throw new PermissionDeniedException("权限不足");
        }

        // 执行目标方法
        return joinPoint.proceed();
    }
}
