Gael Santos

Both Assignment 2 and Assignment 3 entail ETL Pipeline implementations. In Assignment 2, the implementation was done in one class with all methods and variables being in one file. In Assignment 3, the implementation was done with 5 classes, each with their own methods (and occasionally variables), spread across 5 files. Both implementations have the same functionality, where they read from a file, apply transformations on the information read, and then write the results to a new file.

Assignment 2 used only a single class and only static methods. The information read per line was stored in String arrays which were then stored in ArrayLists. The transformations to the information were done directly in the input and output methods. And lastly, the main method went through the whole pipeline sequentially. 

Assignment 3 however, was spread across 5 different and specialized classes. It used non-static methods and required objects to be made in order to use said methods. The Product class allows for the information about the products to be stored in an object with variables and methods instead of an array of Strings. The ETLPipeline class is now able to just run without doing all the logic. 

Assignment 3 was more object oriented because it implements more object-oriented programming principles. 

It applied objects and classes, with 5 unique classes and objects being defined and made in Product and ETLPipeline.

It applied encapsulation in the Product class, by encapsulating all the data and behaviors. Only through select public methods can information within the Product objects be accessed. The same applies for the DataTransformer class, where the logic for how to transform the data is encapsulated in the class, and not exposed to the main logic. 

This implementation did not use inheritance or polymorphism, as they were not necessary.

Though, this implementation was written from an object oriented focus: with 5 classes having 5 different responsibilities; having an execute method which is run in the main method; and with ETLPipeline composing instances of the DataReader, DataTransformer, and DataWriter classes. 

To test to make sure Assignment 3’s implementation had the same functionality as Assignment 2’s, I used identical sample ‘products.csv’ files, one where it was a full list, one where it was a list with just the header, and an empty file. I ran all three versions with the two different implementations, and they produced the exact same results. 
