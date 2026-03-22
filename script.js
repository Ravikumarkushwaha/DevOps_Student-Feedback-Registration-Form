const form = document.getElementById("feedbackForm");
const successMessage = document.getElementById("successMessage");

const fields = {
    name: document.getElementById("name"),
    email: document.getElementById("email"),
    mobile: document.getElementById("mobile"),
    department: document.getElementById("department"),
    comments: document.getElementById("comments")
};

const errors = {
    name: document.getElementById("nameError"),
    email: document.getElementById("emailError"),
    mobile: document.getElementById("mobileError"),
    department: document.getElementById("departmentError"),
    gender: document.getElementById("genderError"),
    comments: document.getElementById("commentsError")
};

function clearErrors() {
    Object.values(errors).forEach((errorElement) => {
        errorElement.textContent = "";
    });

    Object.values(fields).forEach((fieldElement) => {
        fieldElement.classList.remove("invalid");
    });

    successMessage.textContent = "";
}

function setFieldError(fieldName, message) {
    errors[fieldName].textContent = message;

    if (fields[fieldName]) {
        fields[fieldName].classList.add("invalid");
    }
}

function getWordCount(text) {
    const words = text.trim().match(/\S+/g);
    return words ? words.length : 0;
}

function validateForm() {
    clearErrors();

    const name = fields.name.value.trim();
    const email = fields.email.value.trim();
    const mobile = fields.mobile.value.trim();
    const department = fields.department.value;
    const comments = fields.comments.value.trim();
    const gender = document.querySelector('input[name="gender"]:checked');

    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
    const mobilePattern = /^\d{10}$/;

    let isValid = true;

    if (!name) {
        setFieldError("name", "Student Name should not be empty.");
        isValid = false;
    }

    if (!emailPattern.test(email)) {
        setFieldError("email", "Please enter a valid Email ID.");
        isValid = false;
    }

    if (!mobilePattern.test(mobile)) {
        setFieldError("mobile", "Mobile Number must contain exactly 10 digits.");
        isValid = false;
    }

    if (!department) {
        setFieldError("department", "Please select a Department.");
        isValid = false;
    }

    if (!gender) {
        errors.gender.textContent = "Please select a Gender option.";
        isValid = false;
    }

    if (!comments) {
        setFieldError("comments", "Feedback Comments should not be blank.");
        isValid = false;
    } else if (getWordCount(comments) < 10) {
        setFieldError("comments", "Feedback Comments must contain at least 10 words.");
        isValid = false;
    }

    if (!isValid) {
        return false;
    }

    successMessage.textContent = "Form submitted successfully!";
    return true;
}

form.addEventListener("submit", (event) => {
    event.preventDefault();
    validateForm();
});

form.addEventListener("reset", () => {
    setTimeout(() => {
        clearErrors();
    }, 0);
});