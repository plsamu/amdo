# notes

- th:replace="layoutFileName :: layoutName"

        path/to/layoutFileName :: layoutName(args)
        example:
            layouts/layout-test :: layout2(~{::section})
            aka
            layouts/layout-test :: layout2(~{this::section})
- th:fragment="layoutName(args)"

## content html file example

content.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" th:replace="layouts/layout-test :: layout2('About', ~{::section})" lang="en">
<section>
  <h2>About Us</h2>
  <p>This is the about page content.</p>
</section>
</html>
```

## layout-test.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en" th:fragment="layout2(title, content)">
<head>
  <title th:text="${title}">My Website</title>
</head>
<body>
<div th:replace="${content}"></div>
</body>
</html>
```