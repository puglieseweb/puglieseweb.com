# Understanding Regression Algorithms in Machine Learning

## Introduction to Regression

Regression is a supervised learning technique that predicts continuous numerical values by understanding relationships between variables in a dataset. Unlike classification, which predicts categories, regression predicts quantities.

### Types of Regression

#### 1. Simple Linear Regression

* **Definition**: Predicts a dependent variable based on a single independent variable
* **Mathematical Form**: Y = mx + b
  * Y: Dependent variable (output)
  * x: Independent variable (input)
  * m: Coefficient (slope)
  * b: Intercept
* **Example**: Real Estate Price Prediction
  * Input: House square footage
  * Output: House price

#### 2. Multiple Linear Regression

* **Definition**: Predicts dependent variable based on multiple independent variables
* **Mathematical Form**: Y = m₁x₁ + m₂x₂ + ... + mₙxₙ + b
* **Real Estate Example Features**:
  * Square footage
  * Number of bathrooms
  * Number of bedrooms
  * Year built
  * Location

#### 3. Polynomial Regression

* **Definition**: Handles non-linear relationships between variables
* **Mathematical Form**: Y = m₁x₁² + m₂x₂ + b
* **Use Case**: When relationship is exponential or non-linear
  * Example: House price increasing exponentially with number of bedrooms

### Regression vs. Classification

<figure><img src="../../../../../.gitbook/assets/image (15) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

| Aspect             | Regression                 | Classification              |
| ------------------ | -------------------------- | --------------------------- |
| Objective          | Predicts continuous values | Predicts categories/classes |
| Output Type        | Quantitative (numerical)   | Categorical (discrete)      |
| Evaluation Metrics | MSE, RMSE, R-squared       | Accuracy, Precision, Recall |

### Practical Implementation: Linear Regression Example

#### Setup and Data Preparation

```python
# Import required libraries
import pandas as pd
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt

# Read the dataset
df = pd.read_csv('employee.csv')
```

#### Data Exploration

```python
# Select features for analysis
X = df[['age']]  # Independent variable
y = df['salary']  # Dependent variable

# Create and train the model
model = LinearRegression()
model.fit(X, y)

# Print model parameters
print(f"Intercept: {model.intercept_:.2f}")
print(f"Coefficient: {model.coef_[0]:.2f}")
```

#### Visualization

```python
# Plot the regression line
plt.figure(figsize=(10, 6))
plt.scatter(X, y, color='blue', alpha=0.5)
plt.plot(X, model.predict(X), color='red', linewidth=2)
plt.xlabel('Age')
plt.ylabel('Salary')
plt.title('Age vs Salary Linear Regression')
plt.grid(True)
plt.show()
```

#### Making Predictions

```python
# Example prediction
age_test = [[35]]
predicted_salary = model.predict(age_test)
print(f"Predicted salary for age 35: ${predicted_salary[0]:,.2f}")
```

### Best Practices in Regression

#### 1. Data Preparation

* Check for missing values
* Handle outliers
* Normalize/standardize features if needed
* Split data into training and testing sets

#### 2. Model Selection

* Consider relationship type (linear vs non-linear)
* Evaluate complexity needs
* Account for number of features

#### 3. Model Evaluation

* Use appropriate metrics:
  * Mean Squared Error (MSE)
  * Root Mean Squared Error (RMSE)
  * R-squared (R²)
* Validate assumptions:
  * Linearity
  * Independence
  * Homoscedasticity
  * Normality

#### 4. Common Pitfalls to Avoid

* Overfitting
* Multicollinearity in multiple regression
* Extrapolation beyond data range
* Ignoring outliers

### Advanced Considerations

1. Feature Engineering
2. Regularization Techniques
3. Cross-Validation
4. Hyperparameter Tuning
