package ma.izicap.izicap_test_younes_elhakimi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class IzicapTestYounesElhakimiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IzicapTestYounesElhakimiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        new File("answers.csv").delete();
    }
}
