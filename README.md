# Lung-Cancer-detection-app
Overview:
The Lung Cancer Detection App is an Android application designed to assist in the early detection of lung cancer through image analysis. Users can upload chest X-ray images, and the app utilizes a trained machine learning model to predict the likelihood of various lung conditions, including normal, adenocarcinoma, large cell carcinoma, squamous cell carcinoma, and pneumonia.

Features:
Image Upload: Users can upload chest X-ray images from their device.

Real-time Prediction: The app provides real-time predictions based on a pre-trained TensorFlow Lite model.

User-friendly Interface: Simple and intuitive user interface for easy interaction.

How it Works:
Image Processing: The uploaded image undergoes preprocessing, including resizing, to meet the model's input requirements.

Model Inference: The pre-trained machine learning model (BestFloat16) processes the preprocessed image and generates predictions for different lung conditions.

Result Display: The predicted lung condition is displayed to the user, aiding in early detection and potential medical intervention.

Setup:
Clone the repository.

Open the project in Android Studio.

Ensure the necessary dependencies and libraries are correctly configured.

Run the app on an Android emulator or a physical device.

Dependencies:
TensorFlow Lite
AndroidX
Model:
The machine learning model used in this app (BestFloat16) is trained to classify chest X-ray images into different lung conditions. The model is based on TensorFlow Lite and is included in the ml package.

Disclaimer:
This app is intended for educational and informational purposes only. It is not a substitute for professional medical advice, diagnosis, or treatment. Consult with a qualified healthcare professional for medical concerns.

