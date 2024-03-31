document.addEventListener("DOMContentLoaded", () => {
  const filterItems = document.querySelectorAll(".filter-item");
  const works = document.querySelectorAll(".filter-section");

  function showWorks(category) {
    works.forEach((work) => {
      if (category === "All") {
        work.style.display = "block";
      } else {
        const workCategory = work.dataset.category;
        if (workCategory === category) {
          work.style.display = "block";
        } else {
          work.style.display = "none";
        }
      }
    });
  }

  showWorks("All");
  document
    .querySelector('.filter-item[data-category="All"]')
    .classList.add("active");

  filterItems.forEach((filterItem) => {
    filterItem.addEventListener("click", function () {
      const category = this.dataset.category;
      console.log(category);
      showWorks(category);

      filterItems.forEach((item) => {
        item.classList.remove("active");
      });
      this.classList.add("active");
    });
  });
});


function showDetails() {
    const item = document.querySelector(".")
}
