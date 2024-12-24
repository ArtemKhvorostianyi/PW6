ТВ-23, Хворостяний Артем Кирилович, Варіант 5

Напишіть програму, в якій будуть реалізовані наступні задачі:
- запит до користувача обрати Y/N, якщо Y – то виконання задачі кожні
10 секунд з виводом повідомлення про її виконання, якщо N – вивід
повідомлення про пропуск поточної задачі;
- через випадковий інтервал між 1 і 10 секундами виконується задача, яка
виводить поточний час виконання (від запуску програми, а не
системний).

public static void main(String[] args) {
        SpringApplication.run(SpringAsyncDemoApplication.class, args);
    } - запуск проекту

 public void run(String... args) - запит у користувача Y/N, якщо Y - taskService.enableRecurringTask();
                                                            якщо N -  taskService.startRandomIntervalTask();
public void executeRecurringTask()  - виконання задачі в заданому інтервалі часу
public void startRandomIntervalTask() - метод, який задає рандомний інтервал часу у заданому проміжку
 
