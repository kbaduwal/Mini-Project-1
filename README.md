# Mini-Project-1
Application Name: Insurance Report Generation

<!-- As it is web application we need to select war. -->

------------------------------
DB Table: CITIZENS_PLANS_INFO
-------------------------------
CITIZEN_ID           NUMBER      PRIMARY KEY
CITIZEN_NAME         VARCHAR
GENDER               VARCHAR
PLAN_NAME            VARCHAR
PLAN_STATUS          VARCHAR
PLAN_START_DATE      VARCHAR
PLAN_END_DATE        VARCHAR
BENIFIT_AMOUNT       NUMBER
DENIAL_REASON        VARCHAR
TERMINATED_DATE      DATE
TERMINATED_REASON    VARCHAR

-----------------------------------------------------------------------
Java Components: How many class are required to develop the application
-----------------------------------------------------------------------

a)Persistance(Database) Layer Component
1) CitizenPlan.java (Entity Class used to Map with database table)
2) CitizenPlanRepository.java (Interface) (Used to perform operation with the table) (Will be JpaRepository)

b) Service(Business Logic) Layer Component
3) RepoertService.java (Interface) (Write abstract method here.)[Always]
4) ReportServiceImpl.java (class) (Implemention of above abstract method.)
5) PdfGenerator.java (class)
6) ExcelGenerator.java (class)
7) EmailUtils.java (class)

c)Controller (Deals with taking the request and giving respone of the data.)(Usually, how many screen available in UI that many controller are written.)
8) ReportController.java (class)
9) SearchRequest.java (class)(Capture data from the UI and search it.) [This class also called as -> form binding/ command /DTO(Data Transfer Object) class]

Runner(used to load the data to database table)
10) DataLoader.java (runner class)

Presentation File
11) index.jsp
