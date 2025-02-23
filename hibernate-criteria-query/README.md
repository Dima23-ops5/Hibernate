# Hibernate Criteria Query

Imagine, that the user has sent the request with the URL: 
`http://localhost:8080/phones?countryManufactured=China&producer=apple,nokia,samsung&color=white,red`

__Your task is to implement all PhoneDao methods.__

Method findAll() has one input parameter - `Map<String, String[]> params`. It has the following format:
```text
Map<String, String[]> params = new HashMap<>();
params.put("countryManufactured", new String[]{"China"};
params.put("maker", new String[]{"apple", "nokia", "samsung"};
params.put("color", new String[]{"white", "red"};
```
You need to find all phones that match the specified parameters.

#### [Try to avoid these common mistakes, while solving the task](./checklist.md)
