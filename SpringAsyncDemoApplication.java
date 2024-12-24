package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableAsync
public class SpringAsyncDemoApplication implements CommandLineRunner {

    private final TaskService taskService;

    public SpringAsyncDemoApplication(TaskService taskService) {
        this.taskService = taskService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAsyncDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to execute the recurring task every 10 seconds? (Y/N):");
        String input = scanner.nextLine().trim().toUpperCase();

        if ("Y".equals(input)) {
            taskService.enableRecurringTask();
        } else {
            System.out.println("Recurring task skipped.");
        }

        // Start random interval task
        taskService.startRandomIntervalTask();
    }
}

@Service
class TaskService {

    private boolean recurringTaskEnabled = false;
    private final Instant startTime;

    public TaskService() {
        this.startTime = Instant.now();
    }

    public void enableRecurringTask() {
        this.recurringTaskEnabled = true;
        System.out.println("Recurring task enabled. Task will execute every 10 seconds.");
    }

    @Scheduled(fixedRate = 10000)
    public void executeRecurringTask() {
        if (recurringTaskEnabled) {
            System.out.println("Recurring task executed at: " + Instant.now());
        } else {
            System.out.println("Skipping recurring task execution. Task is disabled.");
        }
    }

    @Async
    public void startRandomIntervalTask() {
        while (true) {
            int delay = ThreadLocalRandom.current().nextInt(1, 11); // Random delay between 1 and 10 seconds
            try {
                TimeUnit.SECONDS.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }

            Duration elapsed = Duration.between(startTime, Instant.now());
            System.out.println("Random interval task executed at: " + Instant.now() + ". Time elapsed: " + elapsed.getSeconds() + " seconds.");
        }
    }
}
