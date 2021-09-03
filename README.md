# ISA_Pharmacy

Install Node.js on Windows or if you are Arch Linux user with "sudo pacman -S npm"

Install PosgreSQL and create new database with name newdb25

Open terminal in frontend and type "npm install" ant then "npm run serve"

Open project in Eclipse and start Tim40Application.java

# PHARMACY ADMINISTRATOR 
To test pharmacy administrator please login with pjevalica@gmail.com/bobi (3.4)
to test pharmacy page please login with antonic34@gmail.com/kinzo  (3.8)

3.12 Pharmacy-> workers -> appointments
Select dermatologist and click appointments,choose from  working days and select time and duration. You cannot create 2 appointments on same date/time/duration.
 Dermatologist can work from 7am to 22pm! Dermatologist Ana Kesic is working in other Pharmacy for date 16.sept 2021

3.22 Create action/promotion - insert text and set dates. All subscribers should receive message from pharmacy email ursaminor1777@gmail.com

3.24 / 3.26 Pharmacy->purchase order
Purchase order - You can create purchase order for existing or new medications. New medications are ordered separately from exsisting meds. After ordering new medication it 
will be added to database and then you can order it as any other medication.
Purchase order table is filled with purchase order with statuses OBRADJENA or CEKA_PONUDE. You cannot delete order made by other pharmacy admins or delete your order with offers.
To accept offer please click on preexisting purchase order made by Bojana Pjevalica. All providers will be automatically notified about offer acceptance status.
You can filter table by order status.

3.29 Price list - Enter date from which the price list will be active. If that date is now it will be automatically set to ACTIVE, and price lists except mentioned one will be 
set to INACTIVE. If you choose date and time in the future new price list status will be set to NOT_YET_ACTIVE. Until the date has come.
3.32/ 3.33 On this page you can filter pharmacists or dermatologists. Also you can add appointments for functionality 3.12 . When adding new pharmacist you must select working days. You cannot select same date multiple times. When adding dermatologist to your pharmacy you must set working days. If dermatologist is working in other pharmacy that day you will be notified.
3.38 Accepting absence. You can either accept or refuse. If refuse is clicked worker is notified via mail.

MAPS & REPORT: Map is displayed on pharmacy page (see 3.8) 
to see report page go to Pharmacy->more-> report and generate report :
1.) From current date to one year ago
2.) From current date to four months ago 
3.) This month - no data
4.) For certain period -> from january 2021 there are some dates
5.) Pharmacy statistics
** You can check application to see if messages are sent or you can login to our email and see sent mails(see application properties for credentials) or you can subscribe




# DATA

If you want to test dermatologist login with antonic1@gmail.com/kinzo
If you want to test pharmacist login with antonic11@gmail.com/kinzo

Both users are working between 13.06.2021 and 22.06.2021 and they are working on 13.07.2021

Dermatologist is on holiday between 10.07.2021 and 20.07.2021

When testing scheduling for dermatologist keep in mind that termins are predefinied. See data.sql, all termins where is_taken = false are free.

When testing scheduling for pharmacist keep in mind that only taken consultations are in database, free termins are generating in real time.
