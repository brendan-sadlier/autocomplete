# Autocomplete Component Project

## Overview
This project was build as a showcase for the Autocomplete component. The component is a reusable component that can be used in any project that needs an autocomplete feature. The component is built using React and JavaScript.

Watch It Live: [Project Demonstration]()

## Features
- The component is reusable and can be used in any project.
- The component is built using React and JavaScript.
- The component is fully responsive and works on all screen sizes.
- Fetches and displays suggestions based on user input.
- Supports both local and external search URLs.
- Customizable suggestion format and click behavior.
- Loading indicator while fetching suggestions.
- Clears suggestions when the input is cleared or a suggestion is selected

## Tech Stack

**Frontend:** React, JavaScript, HTML, CSS
**Backend:** Java 11, SpringBoot, Maven

## Run Locally

### Pre-requisites
- Docker
- Maven

### How to Run

1. Clone the project
```bash
  git clone https://github.com/brendan-sadlier/autocomplete
```
2. Go to the project directory
```bash
  cd autocomplete
```

3. Build the project
```bash
  mvn clean package -f backend
```

4. Run the project
```bash
  docker-compose up --build
```

5. The project will be available at `http://localhost:3000` after the build is complete. (This may take a few minutes)

