package com.tecsup.demo.aop;

import com.tecsup.demo.domain.entities.Auditoria;
import com.tecsup.demo.domain.entities.Alumno; // Asegúrate de importar la clase Alumno
import com.tecsup.demo.domain.entities.Curso;
import com.tecsup.demo.domain.persistence.AuditoriaDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;

@Component
@Aspect
public class LogginAspecto {

    private Long tx;

    @Autowired
    private AuditoriaDao auditoriaDao;

    @Around("execution(* com.tecsup.demo.services.*ServiceImpl.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        Long currTime = System.currentTimeMillis();
        tx = System.currentTimeMillis();
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = "tx[" + tx + "] - " + joinPoint.getSignature().getName();
        if (joinPoint.getArgs().length > 0) {
            logger.info(metodo + "() INPUT:" + Arrays.toString(joinPoint.getArgs()));
        }
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage());
        }
        logger.info(metodo + "(): tiempo transcurrido " + (System.currentTimeMillis() - currTime) + " ms.");
        return result;
    }

    @After("execution(* com.tecsup.demo.controllers.*Controller.guardar*(..)) ||" +
            "execution(* com.tecsup.demo.controllers.*Controller.editar*(..)) ||" +
            "execution(* com.tecsup.demo.controllers.*Controller.eliminar*(..))")
    public void auditoria(JoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = joinPoint.getSignature().getName();
        Integer id = null;

        Object[] parametros = joinPoint.getArgs();
        if (metodo.startsWith("guardar")) {
            if (parametros[0] instanceof Curso) {
                Curso curso = (Curso) parametros[0];
                id = curso.getId();
                logger.info("Guardando curso con ID: " + id);
            } else if (parametros[0] instanceof Alumno) {
                Alumno alumno = (Alumno) parametros[0];
                id = alumno.getId();
                logger.info("Guardando alumno con ID: " + id);
            }
        } else if (metodo.startsWith("editar") || metodo.startsWith("eliminar")) {
            if (parametros[0] instanceof Integer) {
                id = (Integer) parametros[0];
                logger.info("Editando o eliminando con ID: " + id);
            } else {
                logger.error("Error: El parámetro no es un Integer válido. Valor recibido: " + parametros[0]);
            }
        }

        String traza = "tx[" + tx + "] - " + metodo;
        logger.info(traza + "(): registrando auditoria...");
        auditoriaDao.save(new Auditoria("entidades", id, Calendar.getInstance().getTime(), "usuario", metodo));
    }
}
