console.log("script")
const baseURL = "http://localhost:3000";


async function logout(){
    Swal.fire({
        title: "Do you want to logout?",
        showDenyButton: true,
        confirmButtonText: "Logout",
        denyButtonText: `Cancel`
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            const url=`${baseURL}/do-logout`;
            window.location.replace(url);
        }
    });
}

async function chat(){
    Swal.fire({
        title: "Do you want to enter chatroom?",
        showDenyButton: true,
        confirmButtonText: "Enter",
        denyButtonText: `Cancel`
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            const url=`${baseURL}/chatroom`;
            window.location.replace(url);
        }
    });
}



