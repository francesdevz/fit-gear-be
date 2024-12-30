package FirGear.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = FitGearApplication.class)
public class FitGearApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitGearApplication.class, args);
	}

}
