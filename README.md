# PracticalFive — Chinese Municipalities

An Android application built for **Practical 5** of the COMPX Android course.

The app lets a user enter the name of one of China's four municipalities —
**Beijing, Shanghai, Tianjin or Chongqing** — validates the input, shows a
factual summary about the selected municipality, customises the screen with the
corresponding image and colour scheme, and opens the official website of the
municipality with an implicit Intent.

## Features

- **Main screen** (`MainActivity`): an image of China's municipalities and an
  `EditText` where the user types a municipality name and presses *Done* on the
  keyboard.
- **TextProcessor**: a plain Java class that validates and normalises the input
  case-insensitively (e.g. `BEIJING` → `Beijing`), returning an invalid result
  for empty or unsupported names.
- **Second screen** (`Municipality`): receives the normalised name through an
  explicit Intent and dynamically sets the name, image, information summary,
  text colour and button colour for the selected municipality.
- **Visit site button**: opens the official English website of the displayed
  municipality using an implicit `ACTION_VIEW` Intent.

## Technology

- Language: **Java**
- UI: **XML layouts** (Empty Views Activity)
- Resources: drawables, launcher icons, strings, colours and a string array of
  municipality summaries in `res/values/arrays.xml`.

## Running the project

Open the `PracticalFive` folder in Android Studio, let Gradle sync, and run the
app on an emulator or device.
