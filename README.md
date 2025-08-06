<img width="1080" height="1920" alt="DetailScreen" src="https://github.com/user-attachments/assets/e0461d72-92da-4e6a-87fa-75b780b93083" />#FakeStore Product Viewer (Jetpack Compose App)

An Android app that fetches products from the [FakeStore API](https://api.escuelajs.co/api/v1/products) and displays them using **Jetpack Compose**, **Retrofit**, **ViewModel**, and **Material 3**.

---

## 📱 Screenshots


| <img width="108" height="192" alt="HomeScreen" src="https://github.com/user-attachments/assets/26e6cd51-1ff8-452b-8ef7-376cd99e8e7a" />
 |<img width="108" height="192" alt="DetailScreen" src="https://github.com/user-attachments/assets/72ba804d-b92c-4eb5-b899-61632d330669" />
 

---

##  Features

- Jetpack Compose UI (Material 3)
- Home screen with list of products using `LazyColumn`
-  Detail screen on click with image, title, price, and description
-  Navigation with arguments
-  HTTP networking with Retrofit & Gson
-  ViewModel with `ResultState` sealed class for loading/success/error
- clean architecture & modular folder structure
- Fully commented, beginner-friendly

---

##  My Learning Journey

This project started as a practice assignment and evolved as I explored more Android architecture concepts. Here's how I progressed:

1.  **Started by following the session instructions** to fetch and display data from FakeStore API
2.  **Searched for the best folder structure** to organize `model`, `network`, `ui`, and `viewmodel`
3.  **Improved the UI** step-by-step with Jetpack Compose and Material 3
4.  **Introduced a sealed wrapper class (`ResultState`)** to cleanly handle `Loading`, `Success`, and `Error` states
5.  **Tested and polished the user experience** by showing loading indicators and retry buttons
6.  **Learned how to read JSON responses**, create matching `data class`, define Retrofit interfaces, and build a reusable Retrofit client
7.  **Created a ViewModel** with coroutine logic and exposed clean state to the UI
8.  **Finalized project using Compose UI best practices**, `NavController`, `TopAppBar`, and more

---

##  Final Thoughts

This project taught me how to:

- Connect UI with live network data using Retrofit and ViewModel
- Build scalable and reactive apps using Jetpack Compose
- Apply clean architecture and separation of concerns across `model`, `network`, `ui`, and `viewmodel`
- Enhance user experience with responsive state management using sealed classes (`ResultState`)

