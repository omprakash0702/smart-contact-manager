console.log("contacts.js loaded");

// =====================
// MODAL CONTROLS
// =====================
function openContactModal() {
  const modal = document.getElementById("view_contact_modal");
  if (!modal) {
    console.error("Modal not found");
    return;
  }
  modal.classList.remove("hidden");
  modal.classList.add("flex");
}

function closeContactModal() {
  const modal = document.getElementById("view_contact_modal");
  if (!modal) return;
  modal.classList.add("hidden");
  modal.classList.remove("flex");
}

// =====================
// 👁️ VIEW CONTACT
// =====================
window.loadContactdata = async function (id) {
  try {
    const res = await fetch(`/api/contacts/${id}`);
    if (!res.ok) throw new Error("API failed");

    const data = await res.json();

    document.getElementById("contact_name").innerText =
      data.name || "Not available";

    document.getElementById("contact_email").innerText =
      data.email || "Not available";

    document.getElementById("contact_phone").innerText =
      data.phoneNumber || "Not available";

    document.getElementById("contact_address").innerText =
      data.address || "Not available";

    document.getElementById("contact_about").innerText =
      data.description || "Not available";

    document.getElementById("contact_favorite").innerText =
      data.favorite ? "⭐ Favorite Contact" : "Not marked";

    const img = document.getElementById("contact_image");
    const avatar = document.getElementById("contact_avatar");

    if (data.picture && data.picture.trim() !== "") {
      img.src = data.picture;
      img.classList.remove("hidden");
      avatar.classList.add("hidden");
    } else {
      img.classList.add("hidden");
      avatar.classList.remove("hidden");
    }

    let social = "NA";
    if (data.websiteLink || data.linkedInLink) {
      social = "";
      if (data.websiteLink) social += `🌐 ${data.websiteLink} `;
      if (data.linkedInLink) social += `💼 ${data.linkedInLink}`;
    }
    document.getElementById("contact_social").innerText = social;

    document.getElementById("edit_contact_btn").href =
      `/user/contacts/view/${id}`;

    openContactModal();

  } catch (err) {
    console.error("Eye click error:", err);
    alert("Unable to load contact details");
  }
};


// =====================
// 🗑 DELETE
// =====================
function deleteContact(id) {
  Swal.fire({
    title: "Delete this contact?",
    text: "This action cannot be undone",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#dc2626",
    confirmButtonText: "Delete",
  }).then((result) => {
    if (result.isConfirmed) {
      window.location.href = `/user/contacts/delete/${id}`;
    }
  });
}
