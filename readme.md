# FINAL PROJECT
> COCREATE APP

## Table of contents
* [Brief Description](#brief-description)
* [Instalasi Requirements](#instalasi-requirements)
* [Download Repository](#download-repository)
* [How To Use](#how-to-use)
* [Author](#author)


## Brief Description
Cocreate App merupakan sebuah aplikasi berbentuk microservice yang memiliki 2 service utama yaitu AUTH dan POST COLLAB.
Untuk mengakses 2 service tersebut, diperlukan 2 service tambahan yaitu API GATEWAY DAN DISCOVERY SERVICE.


## Fitur 
Epic: CoCreate Membership Enrollment
List of Stories:
> Anyone on banking team can join to the CoCreate community using email or social account
> As a member, they can choose which categories they want
> As a member, they can give/update their basic profile information


Epic: CoCreate Membership Enrollment
List of Stories:
> As a member, they can see top and trending dashboard for members, contributions and innovations
> As a member, they can see all relevant posts for their choosen categories 
> As a member, they can see the detail of selected post 
> As a member, they can give comment, like and share the selected post 
> As a member, they can post new article related to new idea/insight/news on particular categories


## Instalasi Requirements
1. JAVA 11
2. Maven
3. IntelliJ (opsional)
4. Kafka

## PORT 
1. 8080 AUTH
2. 8081 POSTCOLLAB
3. 9000 API GATWAY 
4. 8761 DISCOVERY SERVICE


## Download Repository
1. Download atau clone repository berikut ini 
    * https://github.com/dwibagus154/post-collab.git
    * https://github.com/dwibagus154/auth.git
    * https://github.com/dwibagus154/api-gateway.git
    * https://github.com/dwibagus154/discovery-service.git

## How to Use
1. Buka 4 repository yang telah di download tersebut masing masing di text editor kesayangan anda (disarankan Intelij)
2. Jalankan aplikasi
3. Jika menggunakan terminal untuk menjalankan aplikasi, gunakan mvn:spring-boot run

## ERD
![Untitled Diagram-Page-1 drawio](https://user-images.githubusercontent.com/59368258/148170577-696fe782-da6a-4d33-9d25-af7e39c212d0.png)

## High Level Architecture
![Untitled Diagram-Page-3 drawio](https://user-images.githubusercontent.com/59368258/148171621-331a0bcc-56e2-486f-ae04-cca6ed4b5345.png)

## Author
* Kadek Dwi Bagus Ananta Udayana	Teknik Informatika ITB
