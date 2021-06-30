package step;

enum Category {
    квартиры("Квартиры", "24"),
    быттехника("Бытовая техника", "21"),
    растения("Растения", "106"),
    ноутбуки("Ноутбуки", "98"),
    оргтехника("Оргтехника и расходники", "99"),
    велосипеды("Велосипеды", "34"),

    Стандарт("По умолчанию", "101"),
    Дешевле("Дешевле", "1"),
    Дороже("Дороже", "2"),
    Дата("По дате", "104");

    String name;
    String value;

    Category(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

