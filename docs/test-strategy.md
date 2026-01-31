# Test Strategy

## 1. Purpose
This document defines the overall testing approach, scope, and methodology for this coffee shop application. The goal is to ensure critical user journeys function correctly and risks to business value are minimized.

---

## 2. Product Overview
The application is a web-based e-commerce platform that allows users to:
- Browse products
- Add items to a shopping cart
- Complete checkout using a payment provider
- Recieve and order confirmation

The system consists of a frontend web application and a backend API

---

## 3. Testing Objectives
The primary testing objectives are:
- Validate critical end-to-end user flows (browse -> cart -> checkout)
- Ensure correctness of business logic (pricing, totals, discounts)
- Detect defects early through shift-left testing
- Reduce regression risk through targeted automation
- Provide confidence for releases through smoke and regression testing

---

## 4. Scope
**4.1 In Scope**
- Product listing and product details
- Cart functionality (add, remove, update quantity)
- Checkout flow
- Order confirmation
- Basic user authentication
- Backend API funtionality supporting functional features

**4.2 Out of Scope**
- Performance and load testing
- Security and penetration testing
- Mobile native applications
- Third-party payment provider internal behaviour

---

## 5. Test Levels & Types
**Unit Testing**
- Business Logic (cart calculations, pricing rules)
- Input validation

**Integration Testing**
- Backend services interaction
- API communication with mocked services

**System/End-to-End testing**
- User journeys
- Smoke & regression coverage

**Exploratory Testing**
- Error handling
- Edge cases
- UX and usability issues

---

## 6. Manual vs Automation Strategy
**Automated Testing**
- Smoke tests for critical paths
- Regression tests for checkout and cart flows
- API tests for backend endpoints

**Manual Testing**
- Exploratory testing
- New feature validation
- UI/UX assessment
- Negative and edge-case scenarios

Automation will focus on high-value, repeatable test scenarios.

---

## 7. Test Environment
- Local development environment
- Staging environment with production-like configuration
- Mocked external services (e.g., payment provider)

---

## 8. Test Data Strategy
- Test users (guest and registered)
- Valid and invalid payment data
- Boundary values for prices and quantities
- Data reset between test runs when required

---

## 9. Entry & Exit Criteria
**Entry Criteria**
- Feature deployed to test environment
- Acceptance criteria defined
- Required test data available

**Exit Criteria**
- All P0 test scenarios pass
- No open critical defects
- Smoke and regression suites executed successfully

---

## 10. Assumptions & Constraints
- Payment provider behavior is mocked
- Single-browser support for initial testing
- Limited project scope due to portfolio constraints

---

## 11. Deliverables
- Test strategy document
- Test scenarios and test cases
- Automated test suites (UI and API)
- Bug reports and test execution summaries