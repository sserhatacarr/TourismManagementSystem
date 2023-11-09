# Database Design and Sample Data

## Introduction

This document provides the database schema and sample data for three tables: "hotel," "reservation," and "room." It also includes a "user" table schema and sample data for user authentication. The provided SQL scripts allow for the creation of the database tables and insertion of sample data.

## Hotel Table

### Table Creation

```sql
CREATE TABLE hotel
(
    id                integer      null,
    name              varchar(255) null,
    city              varchar(255) null,
    district          varchar(255) null,
    address           varchar(255) null,
    email             varchar(255) null,
    phone_number      varchar(255) null,
    stars             varchar(10)  null,
    facility_features varchar(255) null,
    pension_type      varchar(255) null,
    adult_price       integer      null,
    children_price    integer      null,
    single_stock      integer      null,
    double_stock      integer      null,
    suit_stock        integer      null
);

```
### Sample Data Insertion for Hotel Table

```sql
INSERT INTO hotel (name, city, district, address, email, phone_number, stars, facility_features, pension_type, adult_price, children_price, single_stock, double_stock, suit_stock) VALUES
('Kodluyoruz Life Istanbul', 'İstanbul', 'Beyoğlu', 'Meşrutiyet St. No:125, Şişhane, Beyoğlu, Istanbul', 'info@kodluyoruz.org', '0212 xxx xx xx', '5 Star', 'Free Parking, SPA, 7/24 Room Service', 'Room Breakfast,Half Pension', 200, 100, 20, 30, 10),
('Beach Hotel', 'İzmir', 'Alsancak', 'Kordon St. No:123, Alsancak, Izmir', 'info@sahiloteli.com', '0232 xxx xx xx', '4 Star', 'Free WiFi, Swimming Pool', 'All Inclusive,Room Breakfast', 180, 90, 15, 25, 5),
('Mountain Resort', 'Yalova', 'Termal', 'Orman St. No:55, Termal, Yalova', 'info@dageviresort.com', '0226 xxx xx xx', '4 Star', 'Free Parking, SPA, Fitness Center', 'Ultra All Inclusive,Full Pension', 220, 110, 25, 35, 15),
('Göcek Paradise Hotel', 'Muğla', 'Fethiye', 'Marina St. No:78, Göcek, Fethiye, Muğla', 'info@gocekparadisehotel.com', '0252 xxx xx xx', '5 Star', 'Hotel Concierge, SPA, Free Parking', 'All Inclusive,Full Pension', 240, 120, 30, 40, 20),
('Kapadokya Cave Hotel', 'Nevşehir', 'Ürgüp', 'Kayabaşı St. No:32, Ürgüp, Nevşehir', 'info@kapadokyacavehotel.com', '0384 xxx xx xx', '5 Star', 'Swimming Pool, Free Wifi', 'Half Pension,Room Breakfast', 190, 95, 18, 27, 12);
```
## Reservation Table

### Table Creation

```sql
CREATE TABLE reservation
(
    id            int auto_increment
        primary key,
    contact_name  varchar(255) null,
    contact_phone varchar(20)  null,
    contact_email varchar(255) null,
    hotel_name    varchar(255) null,
    pension_type  varchar(255) null,
    room_type     varchar(255) null,
    room_count    int          null,
    adult_count   int          null,
    child_count   int          null,
    days          int          null,
    entrance_date date         null,
    release_date  date         null,
    price         int          null
);
```
### Sample Data Insertion for Reservation Table

```sql
INSERT INTO reservation (contact_name, contact_phone, contact_email, hotel_name, pension_type, room_type, room_count, adult_count, child_count, days, entrance_date, release_date, price)
VALUES
('John Doe', '555-1234567', 'john@example.com', 'Kodluyoruz Life Istanbul', 'Room Breakfast', 'Single', 2, 2, 1, 7, '2023-11-01', '2023-11-08', 1400),
('Alice Smith', '555-9876543', 'alice@example.com', 'Sahil Hotel', 'All Inclusive', 'Double', 2, 2, 0, 5, '2023-12-15', '2023-12-20', 1400),
('Bob Johnson', '555-5555555', 'bob@example.com', 'Mountain Resort', 'Full Pension', 'Single', 1, 0, 1, 4, '2023-10-10', '2023-10-14', 880),
('Eve Wilson', '555-7778888', 'eve@example.com', 'Gocek Paradise Hotel', 'Half Pension', 'Double', 2, 2, 1, 7, '2023-09-05', '2023-09-12', 1680),
('Michael Davis', '555-2223333', 'michael@example.com', 'Cappadocia Cave Hotel', 'Just Bed', 'Single', 1, 1, 0, 3, '2023-10-25', '2023-10-28', 570);

```

## Room Table

### Table Creation

```sql
CREATE TABLE room
(
    id           int auto_increment
        primary key,
    hotel_name   varchar(255) not null,
    room_type    varchar(255) not null,
    stock        int          not null,
    bed_count    varchar(255) null,
    television   varchar(255) null,
    minibar      varchar(255) null,
    game_console varchar(255) null,
    square_meter varchar(255) null,
    till         varchar(255) null,
    projection   varchar(255) null
);
```

### Sample Data Insertion for Room Table

```sql
INSERT INTO room (hotel_name, room_type, stock, bed_count, television, minibar, game_console, square_meter, till, projection)
VALUES
('Kodluyoruz Life Istanbul', 'Single', 20, '1 double bed', 'Yes', 'Yes', 'No', '20 sqm', 'No', 'No'),
('Kodluyoruz Life Istanbul', 'Double', 30, '2 single beds', 'Yes', 'Yes', 'No', '30 sqm', 'Yes', 'No'),
('Beach Hotel', 'Single', 15, '1 double bed', 'Yes', 'No', 'No', '18 sqm', 'No', 'No'),
('Beach Hotel', 'Double', 25, '1 double bed, 1 single bed', 'Yes', 'No', 'No', '25 sqm', 'No', 'No'),
('Mountain Resort', 'Single', 25, '1 king-size bed', 'Yes', 'Yes', 'Yes', '22 sqm', 'No', 'Yes'),
('Mountain Resort', 'Double', 35, '2 single beds', 'Yes', 'Yes', 'No', '30 sqm', 'Yes', 'Yes'),
('Gocek Paradise Hotel', 'Single', 30, '1 double bed', 'Yes', 'Yes', 'Yes', '24 sqm', 'No', 'No'),
('Gocek Paradise Hotel', 'Double', 40, '1 king-size bed, 1 single bed', 'Yes', 'Yes', 'Yes', '35 sqm', 'Yes', 'Yes'),
('Cappadocia Cave Hotel', 'Single', 18, '1 double bed', 'No', 'No', 'No', '15 sqm', 'No', 'No'),
('Cappadocia Cave Hotel', 'Double', 27, '1 king-size bed, 1 single bed', 'Yes', 'No', 'No', '28 sqm', 'No', 'No');
```

## User Table (Authentication)

### Table Creation

```sql
CREATE TABLE user
(
    id         bigint unsigned auto_increment
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    uname      varchar(255) null,
    email      varchar(255) null,
    password   varchar(255) null,
    type       varchar(255) null
);

```
### Sample Data Insertion for User Table

```sql
INSERT INTO user (first_name, last_name, uname, email, password, type) VALUES
('John', 'Doe', 'johndoe', 'johndoe@gmail.com', 'pass123', 'admin
('Alice', 'Smith', 'alicesmith','alicesmith@gmail.com', '123alice', 'employee');
```