SMART CITY UTILITY CONTROL SYSTEM
 
Use Case:
A Smart City Operations Center manages electricity, water, and internet services for citizens and businesses.
An operator uses a console-based application to calculate bills for different services and customer categories.
The operator selects a service, enters usage details, and the system calculates the bill using rule-based logic.
The system must run continuously and allow the operator to switch between services until they choose to exit.
 
Constraints:
- Must be menu-driven using switch
- Use Scanner for input
 
Main Menu:
1. Electricity Service
2. Water Service
3. Internet Service
4. Exit
 
 
1. Electricity Service:
Sub Menu:
1. Domestic Connection
2. Commercial Connection
3. Industrial Connection
4. Back
 
Ask:
- Units Consumed (int)
 
Billing Rules:
 
Domestic:
0–100 units -> Rs. 2 per unit
101–300 units -> Rs. 3 per unit
301+ units -> Rs. 5 per unit
Fixed Charge = Rs. 50
If bill > 2000 -> Add 10% surcharge
 
Commercial:
0–200 -> Rs. 5 per unit
201–500 -> Rs. 7 per unit
501+ -> Rs. 10 per unit
Fixed Charge = Rs. 150
 
Industrial:
Flat Rs. 12 per unit
Fixed Charge = Rs. 500
If units > 1000 -> 15% rebate
 
Display:
Connection Type
Units
Energy Charge
Fixed Charge
Extra / Rebate
Total Bill
 
2. Water Service:
Sub Menu:
1. Residential
2. Society
3. Factory
4. Back
 
Ask:
- Number of persons / taps / machines
 
Rates:
Residential -> Rs. 30 per person
Society -> Rs. 25 per tap
Factory -> Rs. 100 per machine
 
If total > 3000 -> Add 8% tax
 
3. Internet Service:
Sub Menu:
1. Student Plan
2. Home Plan
3. Business Plan
4. Back
 
Ask:
- Duration (1, 3, 6 months)
 
Student:
1 -> Rs. 299
3 -> Rs. 799
6 -> Rs. 1499
 
Home:
1 -> Rs. 499
3 -> Rs. 1399
6 -> Rs. 2699
 
Business:
1 -> Rs. 999
3 -> Rs. 2799
6 -> Rs. 5499
 
If duration is 6 months -> Apply 5% discount
 
Program Rules:
- Use switch for every menu
- Use default to handle invalid input
- User must be able to return to main menu
- Exit only when option 4 is chosen in main menu
- No arrays, no objects, no extra classes
 
