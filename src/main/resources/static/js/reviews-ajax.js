const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
    const res = JSON.parse(xhr.response)
    const container = document.querySelector('.container')

    console.log({res})

    res.forEach(function(review) {
      const reviewItem = document.createElement('div')

      const title = document.createElement('h2')
      title.innerText = review.title;

      const content = document.createElement('p')
      content.innerText = review.content;

      const tags = [];
      review.tagsUrls.forEach(tagUrl => {
        const tagUrlElement = document.createElement('li')
        tagUrlElement.innerHTML = `review tags: <a href="${tagUrl}">${tagUrl}</a>`;
        tags.push(tagUrlElement);
      })

      container.appendChild(reviewItem)
      reviewItem.appendChild(title);
      reviewItem.appendChild(content);

      tags.forEach(tagUrl => reviewItem.appendChild(tagUrl));
    })
  }
}

xhr.open('GET', 'http://localhost:8080/show-reviews', true)
xhr.send()
