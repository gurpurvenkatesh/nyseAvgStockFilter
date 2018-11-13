# nyseAvgStockFilter
Mapreduce Program to demostrate the uasge of Filters

Problem Statement : Get average stock volume per month for the year 2012 & 2013 for few handful of stocks. 
			i. Input : Command line argument with -D for providing the Stock names which needed to be filtered.
			ii. Code Approach
				1. Override setup method in the mapper class to read the input filter arguments..
        2. In the map function add the condition. 
