#Categorisation with Decision Trees

## Scenario
 
An hospital is in need of an automated system that correctly categorises a patients with a lense type for their eyes. 

 Below is the lense data to train the decision tree. This data is used to train the decision tree. After the program reads in this data, the program will be able to categorise any patient's conditions in the future.

  <img src="README_Images/LenseData.png" heigh=140 width=80>

  i.e. the row '5  1  2  1  1  3'.
- 5 is the row's number.
- The next four numbers '1  2  1  1' are the feature's inputs
- The last number '3' is the category of the row (AKA the result of the 4 inputs).
- **Below is a table repesenting the case of row number 5**

**Table 1**
| Feature        | Meaning |                        Attribute information                 | Row 5 |
| -------------  |:-------:|:-----------------------------------------------------------: | -----:|
| Feature 1      | age of the patient:    | (1) young, (2) pre-presbyopic, (3) presbyopic |   1   |
| Feature 2      | spectacle prescription:|  (1) myope, (2) hypermetrope                  |   2   |
| Feature 3      | astigmatic:            |(1) no, (2) yes                                |   1   |
| Feature 4      | tear production rate:  |  (1) reduced, (2) normal                      |   1   |
| Class          | contact lens type:     |  1 : the patient should be fitted with hard contact lenses<br/>2 : the patient should be fitted with soft contact lenses<br/>3 : the patient should not be fitted with contact lenses.                      |   3   |
- This data is from the UCI benchmark, and here is the [description](https://archive.ics.uci.edu/ml/datasets/lenses "Lense Data Description") if you're interested.


## Approach 1: Control Flow Tree

Equation of a line is made using 2 hard-coded points. Below is the result of hard-coding two points to determine the best linear classifer.
If, else if, else conditions are used to evaluate the categories. This method works but is not a learning method. 

Below is the result of this approach, the ouput is the lens type required for the patient. The image below demonstrates a test to see if the actual results match the expected results.

<img src="README_Images/ApplicationControlFlowTree.png" heigh=200 width=200>

## Approach 2: Hard-coded Decision Tree

1. Branches of the tree denote the outcome of the decisions/features.
2. The branches are hard-coded however this is a very efficient method of finding the lens required for a patient since it breaks down decisions required.
Below is a diagram of this approach.
3. In the diagram below, 'F' represents a feature and '1','2','3' represent the class/result of the decisions made by the patient.
4. For instance, the very top branch (the root) contains 'F4', this means Feature 4. If the user decided they had a 'reduced tear production rate' (which is '1'), then 'the patient should not be fitted with contact lenses', this is the left branch from 'F4' (which is '3'). Refer to 'Table 1' for details. 
<img src="README_Images/HardCodedTreeDiagram.png" heigh=700 width=700>

This approach is sufficiently accurate for this dataset.
Below is the console interaction and output for this program:

<img src="README_Images/ApplicationHardCodedTree.png" heigh=500 width=500>
