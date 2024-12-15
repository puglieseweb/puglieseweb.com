# Perform Feature Engineering Using Amazon SageMaker

##

### Introduction

In this lab, you will learn how to perform various data preprocessing tasks and convert raw data so that underlying data patterns can be easily captured. You will be presented with a dataset that contains both numerical and categorical data. You will learn how to encode, scale, and bin the data using scikit-learn.

### Solution

#### Launch SageMaker Notebook

1. To avoid issues with the lab, open a new Incognito or Private browser window to log in. This ensures that your personal account credentials, which may be active in your main window, are not used for the lab.
2. Log in to the AWS Management Console using the credentials provided on the lab instructions page. Make sure you're using the _us-east-1_ region. If you are prompted to select a kernel, please choose **conda\_tensorflow2\_p310**.
3. In the search bar, navigate to **Amazon SageMaker**.
4. Under the _Notebooks_ section in the left menu, click **Notebook Instances**.
5. Confirm the notebook is marked as _InService_. If so, click the **Open Jupyter** link under _Actions_.
6. Click on the **Feature\_Engineering.ipnyb** file.

#### Load Libraries and Prepare the Data

1. Click the first cell that imports the required Python libraries to highlight it, and use the **Run** button at the top to execute the code.
2. An asterisk inside the square braces, (**In \[\*]**), indicates the code is running. You will see a number, (**In\[1]**), once the execution is complete. This first cell uses the Pandas library and reads the raw data from Employee\_encoding.csv.
3.  The second cell requires you to write the code to read the input file and load the dataframe. Paste the following Python code and click **Run**.

    ```python
    employee_df = pd.read_csv('Employee_encoding.csv')
    employee_df.head()
    ```

#### Apply Encoding Techniques

1. In the next cell under _3.1) Ordinal ENcoding_, you will initialize the **OrdinalEncoder** and perform the **fit** operation. Highlight this cell and click **Run**.
2. The next cell uses the **categories\_** attribute to display the encoder's sequence. Highlight this cell and click **Run**.
3.  In the third cell, insert and **Run** the following Python code to assign the transformed values to a new feature named **encoded\_title**.

    ```python
    employee_df['encoded_title'] = ordinal_encoder.transform(employee_df['title'].values.reshape(-1,1))
    employee_df.head()
    ```
4. In the first cell, under _3.2) One-hot Encoding_, the code initializes the **OneHotEncoder**. Highlight this cell and **Run** it.
5.  In the next cell, use **fit\_transform** to perform **fit** and **transform** in a single function call. Update the cell with the following Python code and **Run** it.

    ```python
    transform = gender_encoder.fit_transform(employee_df['gender'].values.reshape(-1,1))
    ```
6. In the third cell, use the **todense** function to address the sparse nature of the data and join the output with the parent dataframe. Highlight this cell and **Run** it.
7.  In the first cell, under _3.3) Label Encoding_, paste and **Run** the following code to initialize **LabelEncoder**.

    ```python
    department_encoder = LabelEncoder()
    ```
8. The next cell applies **fit** and **transform** on the department feature and assigns the output to **encoded\_department**. Highlight this cell and click **Run**.

#### Apply Scaling Techniques

1. In the first cell under _4) Scaling Techniques_, the code scales the salary feature using **MinMaxScaler**. Highlight this cell and click **Run**.
2.  In the next cell, paste and **Run** the below code to invoke the describe function on the **salary\_minmax\_scaled** feature and ensure the value ranges between 0 and 1.

    ```python
    employee_df[['salary_minmax_scaled']].describe()
    ```

#### Apply Binning Techniques

1.  In the first cell under _5) Binning Techniques_, paste and **Run** the following Python code to initialize **KBinDiscretizer** with ten bins.

    ```python
    kbins = KBinsDiscretizer(n_bins=10, strategy='quantile', encode='ordinal')
    ```
2. Highlight the next cell and **Run** it to invoke **fit\_transform** on the **Kbins** discretizer.
3. Highlight and **Run** the code in the last cell to visualize the new **age\_bin** feature using Matplotlib's histogram function.

### Conclusion

Congratulations — you've completed this hands-on lab!
