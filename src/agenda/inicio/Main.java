package agenda.inicio;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import agenda.config.Config;

public class Main {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		new AnnotationConfigApplicationContext(Config.class);
	}
}
