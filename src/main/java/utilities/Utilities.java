package utilities;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class Utilities {

	
	@Produces
	private Logger createLogger(InjectionPoint caller){
        return Logger.getLogger(caller.getMember().getDeclaringClass().getName());
	}
}
