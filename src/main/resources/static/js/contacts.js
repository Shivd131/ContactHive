console.log("Contacts.js");
const baseURL = "http://ec2-65-0-251-247.ap-south-1.compute.amazonaws.com:8081";
const viewContactModal = document.getElementById("default-modal");

// options with default values
const options = {
  placement: "bottom-right",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => {
    console.log("modal is hidden");
  },
  onShow: () => {
    setTimeout(() => {
      contactModal.classList.add("scale-100");
    }, 50);
  },
  onToggle: () => {
    console.log("modal has been toggled");
  },
};

// instance options object
const instanceOptions = {
  id: "default-modal",
  override: true,
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal() {
  console.log("openContactModal");
  contactModal.show();
}

function closeContactModal() {
  contactModal.hide();
}

async function loadContactData(id) {
  try {
    const response = await fetch(`${baseURL}/api/contacts/${id}`);

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const rawText = await response.text();
    console.log("Raw response:", rawText);

    const data = JSON.parse(rawText);
    console.log("Parsed data:", data);

    document.querySelector("#contact-name").textContent = data.name;
    document.querySelector("#contact-email").textContent = data.email;
    document.querySelector("#contact-phone").textContent = data.phoneNumber;
    document.querySelector("#contact-address").textContent = data.address;
    document.querySelector("#contact-description").textContent =
      data.description;
    document.querySelector("#contact-picture").src = data.picture;
    document.querySelector("#contact-linkedin").href = data.linkedInLink;
    document.querySelector("#contact-website").href = data.websiteLink;

    openContactModal();
    return data;
  } catch (error) {
    console.error("Error loading contact:", error);
    throw error;
  }
}

// delete contact

async function deleteContact(id) {
  console.log("deleteContact", id);
  const confirmation = confirm("Are you sure you want to delete this contact?");
  if (confirmation) {
    const url = `${baseURL}/user/contacts/delete/` + id;
    window.location.replace(url);
    alert("Contact deleted successfully.");
  } else {
    alert("Contact deletion cancelled.");
  }
}
