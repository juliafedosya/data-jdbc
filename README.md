[How to run application](#how-to-run-application)

[Spanner](#spanner)

***
## How to run application
To run the application, you need to configure Spanner database and make sure your port 8090 is not used.

***

## Spanner


1. Install [Cloud SDK](https://cloud.google.com/sdk/docs#install_the_latest_cloud_tools_version_cloudsdk_current_version)
2. [Initialize the SDK](https://cloud.google.com/sdk/docs/quickstart-windows#initialize_the_sdk)
3. Get [credentials](https://cloud.google.com/docs/authentication/getting-started)

More information:  
https://cloud.google.com/sdk/docs/quickstart-windows
https://cloud.google.com/spanner/docs/getting-started/set-up  
https://cloud.google.com/sdk/docs/authorizing

Add (change) properties to application.properties
```
spring.cloud.gcp.spanner.project-id=[MY_PROJECT_ID]
spring.cloud.gcp.spanner.instance-id=[MY_INSTANCE_ID]
spring.cloud.gcp.spanner.database=[MY_DATABASE]
```

You may go and create a spanner instance [here](https://console.cloud.google.com/spanner/instances)

Alternatively, use this command to create instance via gcloud CLI:

`$ gcloud spanner instances create yourinst --config=regional-europe-west1 --description=your-inst --nodes=1`

To create database, use this command :

`$ gcloud spanner databases create yourdb --instance=yourinst `

To delete database, use this command :

`$ gcloud spanner databases delete hospital --instance=yourinst `
***
## Create tables

To create all tables, paste the following commands on gcloud consequently:

`$ gcloud spanner databases ddl update hospital --instance=yourinst --ddl='CREATE TABLE HOSPITALS (
     HOSPITAL_ID STRING(MAX) NOT NULL,
     ADDRESS STRING(MAX) NOT NULL,
     NAME STRING(MAX) NOT NULL,
   ) PRIMARY KEY (HOSPITAL_ID);'
    `

  `$  gcloud spanner databases ddl update hospital --instance=testinst --ddl='CREATE TABLE PATIENT_DIAGNOSES (
        HOSPITAL_ID STRING(MAX) NOT NULL,
        PATIENT_ID STRING(MAX) NOT NULL,
        DIAGNOSIS_ID STRING(MAX) NOT NULL,
        DATE_CONFIRMED DATE,
        DETAILS STRING(MAX),
        DOCTOR_ID STRING(MAX),
        REMARKS STRING(MAX),
      ) PRIMARY KEY (HOSPITAL_ID, PATIENT_ID, DIAGNOSIS_ID),
      INTERLEAVE IN PARENT PATIENTS ON DELETE CASCADE;'`
     
  `$ gcloud spanner databases ddl update hospital --instance=testinst --ddl='CREATE TABLE PATIENTS (
           HOSPITAL_ID STRING(MAX) NOT NULL,
           PATIENT_ID STRING(MAX) NOT NULL,
           DATE_OF_BIRTH DATE,
           FIRST_NAME STRING(MAX),
         ) PRIMARY KEY (HOSPITAL_ID, PATIENT_ID),
         INTERLEAVE IN PARENT HOSPITALS ON DELETE CASCADE;'`
       
  `$ gcloud spanner databases ddl update hospital --instance=testinst --ddl='CREATE TABLE PATIENT_DIAGNOSES (
        HOSPITAL_ID STRING(MAX) NOT NULL,
        PATIENT_ID STRING(MAX) NOT NULL,
        DIAGNOSIS_ID STRING(MAX) NOT NULL,
        DATE_CONFIRMED DATE,
        DETAILS STRING(MAX),
        DOCTOR_ID STRING(MAX),
        REMARKS STRING(MAX),
      ) PRIMARY KEY (HOSPITAL_ID, PATIENT_ID, DIAGNOSIS_ID),
      INTERLEAVE IN PARENT PATIENTS ON DELETE CASCADE;'`
     
  `$ gcloud spanner databases ddl update hospital --instance=testinst --ddl='CREATE TABLE DEPARTMENTS (
        HOSPITAL_ID STRING(MAX) NOT NULL,
        DEPARTMENT_ID STRING(MAX) NOT NULL,
        NAME STRING(MAX) NOT NULL,
      ) PRIMARY KEY (HOSPITAL_ID, DEPARTMENT_ID),
      INTERLEAVE IN PARENT HOSPITALS ON DELETE CASCADE;'`
  
  `$ gcloud spanner databases ddl update hospital --instance=testinst --ddl='CREATE TABLE DOCTORS (
        DEPARTMENT_ID STRING(MAX) NOT NULL,
        HOSPITAL_ID STRING(MAX) NOT NULL,
        DOCTOR_ID STRING(MAX) NOT NULL,
        DATE_OF_BIRTH DATE,
        FIRST_NAME STRING(MAX),
      ) PRIMARY KEY (HOSPITAL_ID, DEPARTMENT_ID, DOCTOR_ID),
      INTERLEAVE IN PARENT DEPARTMENTS ON DELETE CASCADE;'`   
***
## Add test data to Spanner
To insert some test data, paste the following commands on gcloud consequently:

`$ gcloud spanner databases execute-sql hospital --instance=testinst --sql="INSERT HOSPITALS (HOSPITAL_ID, ADDRESS, NAME)
                                                                             VALUES ('1', 'Marc', 'Richards'),
                                                                             ('2','AdventHealth Altamonte Springs','Altamonte'),
                                                                             ('3','Delray Medical Center','Palm Beach'),
                                                                             ('4','DeSoto Memorial Hospital','Palm Beach'),
                                                                             ('5','Ed Fraser Memorial Hospital','Macclenny'),
                                                                             ('6','Community Hospital','Sharlotte'),
                                                                             ('7','Glades General Hospital','Palm beach Miami');"`

`$ gcloud spanner databases execute-sql hospital --instance=testinst --sql="INSERT DEPARTMENTS (HOSPITAL_ID, DEPARTMENT_ID, NAME)
                                                                             VALUES ('1', '1', 'general'),
                                                                             ('1', '2', 'surgery'),
                                                                             ('1', '3', 'emergency'),
                                                                             ('2', '4', 'surgery'),
                                                                             ('2', '5', 'emergency'),
                                                                             ('2', '6', 'recovery'),
                                                                             ('2', '7', 'general'),
                                                                             ('5', '8', 'reception'),
                                                                             ('5', '9', 'laboratory');"`

`$ gcloud spanner databases execute-sql hospital --instance=testinst --sql="INSERT DOCTORS (HOSPITAL_ID, DEPARTMENT_ID, DOCTOR_ID, FIRST_NAME,DATE_OF_BIRTH)
                                                                             VALUES ('1', '1','1', 'Rick Ross','1980-08-16'),
                                                                             ('1', '1','2', 'Mr. Komarovsky','1980-08-16'),
                                                                             ('1', '1','3', 'Jack Axeman','1980-08-16'),
                                                                             ('1', '1','4', 'Richard Cheese','1980-08-16'),
                                                                             ('1', '1','5', 'Anna Esman','1980-08-16'),
                                                                             ('1', '2','6', 'Caroline Jones','1980-08-16'),
                                                                             ('1', '3','7', 'Rick Ross','1980-08-16'),
                                                                             ('2', '4','8', 'Angelina Jolie','1980-08-16'),
                                                                             ('5', '8','9','Michael Smith','1980-08-16'),
                                                                             ('5', '9','10', 'Victoria Smith','1980-08-16');"`

`$ gcloud spanner databases execute-sql hospital --instance=testinst --sql="INSERT PATIENTS (HOSPITAL_ID,PATIENT_ID, FIRST_NAME,DATE_OF_BIRTH)
                                                                             VALUES ('1', '11', 'Nina Ricci','1980-08-16'),
                                                                             ('1', '12', 'Giorgio Armani','1980-08-16'),
                                                                             ('1', '13', 'Donatello Versace','1980-08-16'),
                                                                             ('1', '14', 'Christian Dior','1980-08-16'),
                                                                             ('1', '15', 'Victoria Smith','1980-08-16'),
                                                                             ('1', '2', 'Anna Jones','1980-08-16'),
                                                                             ('1', '3', 'Phoebe Buffe','1980-08-16'),
                                                                             ('2', '4', 'Joey Tribiani','1980-08-16'),
                                                                             ('5', '8', 'Monica Geller','1980-08-16'),
                                                                             ('5', '9', 'Chendler Bing','1980-08-16');"`

`$ gcloud spanner databases execute-sql hospital --instance=testinst --sql=" INSERT PATIENT_DIAGNOSES (HOSPITAL_ID,PATIENT_ID,DIAGNOSIS_ID,DOCTOR_ID,DETAILS,REMARKS,DATE_CONFIRMED)
                                                                             VALUES ('1', '11','1','1', 'Allergy','none','2020-01-01'),
                                                                             ('1', '12','2','1', 'Allergy','none','2020-01-01'),
                                                                             ('1', '12','11','1', 'dermatitis','none','2020-01-01'),
                                                                             ('1', '13','3','1', 'Allergy','none','2020-01-01'),
                                                                             ('1', '14','4','1', 'Anemia','none','2020-01-01'),
                                                                             ('1', '15','5','1', 'stomach injury','none','2020-01-01'),
                                                                             ('1', '2','6','1', 'Poisoning','none','2020-01-01'),
                                                                             ('1', '3','7','1', 'Injury','none','2020-01-01'),
                                                                             ('2', '4','8','1', 'Injury','none','2020-01-01'),
                                                                             ('5', '8','9','1', 'Poisoning','serious poisoning','2020-01-01'),
                                                                             ('5', '9','10','1', 'Liver failure','none','2020-01-01');"`
***
*To be continued...*