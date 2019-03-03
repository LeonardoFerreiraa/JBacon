# JBacon :bacon:

JBacon is a tool inspired in Factory Bot to help organize and generate fake objects for tests. The difference between other tools is that JBacon allows easy integration for persist the generated data into a test database.

# Instaling

Maven:

```xml
<dependency>
    <groupId>br.com.leonardoferreira</groupId>
    <artifactId>JBacon</artifactId>
    <version>1.2.1</version>
    <scope>test</scope>
</dependency>
```

Gradle:

```groovy
testImplementation "br.com.leonardoferreira:JBacon:1.2.1"
```

# Usage

It is recommended use with some fake data generator lib like [java-faker](https://github.com/DiUS/java-faker)

Define factory class:

```java
@Component
public class ContactFactory extends JBacon<Contact> {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    protected Contact getDefault() {
        Faker faker = new Faker(new Locale("pt", "BR"));
        Contact contact = new Contact();
        contact.setName(faker.name().fullName());
        contact.setEmail(faker.internet().emailAddress());
        contact.setPhone(faker.phoneNumber().phoneNumber());
        return contact;
    }

    @Override
    protected Contact getEmpty() {
        return new Contact();
    }

    @Override
    protected void persist(Contact contact) {
        contactRepository.save(contact);
    }
}
```

## Using factories

```java
// Returns a Contact instance that not saved, based on default
Contact build = contactFactory.build();

// Returns a list of Contact instance that not saved, based on default
List<Contact> buildList = contactFactory.build(5);

// Returns a saved Contact instance, based on default
Contact create = contactFactory.create();

// Returns a saved list of Contact instance, based on default
List<Contact> createList = contactFactory.create(5);
```
## Using examples

JBacon allows create object based on example.

```java
// All contacts create/build with this example will have "Leonardo Ferreira" as name, 
// and others attributes will be fill based on getDefault
Contact example = new Contact();
example.setName("Leonardo Ferreira");

Contact build = contactFactory.build(example);

List<Contact> buildList = contactFactory.build(5, example);

Contact create = contactFactory.create(example);

List<Contact> createList = contactFactory.create(5, example);
```

Other example syntax supported:

```java
Contact build = contactFactory.build(empty -> {
    empty.setName("Leonardo Ferreira");
    empty.setEmail("mail@leonardoferreira.com.br");
});

List<Contact> buildList = contactFactory.build(5, empty -> {
    empty.setName("Leonardo Ferreira");
    empty.setEmail("mail@leonardoferreira.com.br");
});

Contact create = contactFactory.create(empty -> {
    empty.setName("Leonardo Ferreira");
    empty.setEmail("mail@leonardoferreira.com.br");
});

List<Contact> createList = contactFactory.create(5, empty -> {
    empty.setName("Leonardo Ferreira");
    empty.setEmail("mail@leonardoferreira.com.br");
});

```

## Using templates

```java
@Component
public class ContactFactory extends JBacon<Contact> {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    protected Contact getDefault() {
        Faker faker = new Faker(new Locale("pt", "BR"));
        Contact contact = new Contact();
        contact.setName(faker.name().fullName());
        contact.setEmail(faker.internet().emailAddress());
        contact.setPhone(faker.phoneNumber().phoneNumber());
        return contact;
    }

    @JBaconTemplate("invalid")
    protected Contact invalidContact() {
        Faker faker = new Faker(new Locale("pt", "BR"));
        Contact contact = new Contact();
        contact.setName(faker.name().fullName());
        contact.setEmail("invalid_email");
        contact.setPhone(faker.phoneNumber().phoneNumber());
        return contact;
    }

    @Override
    protected Contact getEmpty() {
        return new Contact();
    }

    @Override
    protected void persist(Contact contact) {
        contactRepository.save(contact);
    }
}
```

```java
// Returns a Contact instance that not saved, based on invalid template
Contact build = contactFactory.build("invalid");

// Returns a list of Contact instance that not saved, based on invalid template
List<Contact> buildList = contactFactory.build(5, "invalid");

// Returns a saved Contact instance, based on invalid template
Contact create = contactFactory.create("invalid");

// Returns a saved list of Contact instance, based on invalid template
List<Contact> createList = contactFactory.create(5, "invalid");
```
# ChangeLog

# 1.2.1 - 2018-10-21
- [Change BeanUtils to avoid call Getters and Setters](https://github.com/LeonardoFerreiraa/JBacon/pull/7)

# 1.2.0 - 2018-10-14
- [Allow use lambda to give a example #6](https://github.com/LeonardoFerreiraa/JBacon/issues/5)

# 1.1.2 - 2018-05-23
- Using lombok only in compile

# 1.1.0 - 2018-03-11
- [BuildList operation #2](https://github.com/LeonardoFerreiraa/JBacon/issues/2)
- Large refactor in project structure

# 1.0.1 - 2018-02-13
- [Avoid direct call to getDefault method #1](https://github.com/LeonardoFerreiraa/JBacon/issues/1)

# 1.0.0 - 2018-02-04
- JBacon first release :beers:
- Create operation
- CreateList operation
- Build operation
