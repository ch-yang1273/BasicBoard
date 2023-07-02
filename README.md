# Basic Board

## ERD

```mermaid
classDiagram
    class Account {
        -Long id
        -String email
        -String password
        -String nickname
        -AccountRole role
    }

    class Post {
        -Long id
        -String title
        -Account author
        -Board board
        -PostContent content
        -int viewCount
        -boolean isDeleted
    }

    class PostContent {
        -Long id
        -String content
    }

    class Board {
        -Long id
        -String name
    }

    class Comment {
        -Long id
        -Post post
        -Comment parentComment
        -Account author
        -String content
        -boolean isDeleted
    }

    class PostLike {
        -Long id
        -Post post
        -Account liker
    }

    Account "1" <-- "0..*" Post
    Account "1" <-- "0..*" Comment
    Account "1" <-- "0..*" PostLike
    Account "1" --> "1" AccountRole
    Post "1" --> "1" PostContent
    Post "1" <-- "0..*" Comment
    Post "1" <-- "0..*" PostLike
    Post "1" --> "1" Board
    Comment "1" <-- "0..*" Comment

```

## 테이블 생성

### Account

<details>

```sql
CREATE TABLE account (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(255),
    role VARCHAR(255),
    create_time TIMESTAMP,
    modified_time TIMESTAMP
);
```

</details>

### Post

<details>

```sql
CREATE TABLE post (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    content_id BIGINT,
    author_id BIGINT,
    board_id BIGINT,
    view_count INT DEFAULT 0,
    is_deleted BOOLEAN DEFAULT FALSE,
    create_time TIMESTAMP,
    modified_time TIMESTAMP
);
```

</details>

### PostContent

<details>

```sql
CREATE TABLE post_content (
    id BIGINT PRIMARY KEY,
    content VARCHAR(1000)
);
```

</details>

### PostLike

<details>

```sql
CREATE TABLE post_like (
    id BIGINT PRIMARY KEY,
    post_id BIGINT,
    liker_id BIGINT,
    create_time TIMESTAMP,
    modified_time TIMESTAMP
);

```

</details>

<details>

```sql
CREATE TABLE board (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    create_time TIMESTAMP,
    modified_time TIMESTAMP
);
```

</details>

<details>

```sql
CREATE TABLE comment (
    id BIGINT PRIMARY KEY,
    content TEXT,
    post_id BIGINT,
    author_id BIGINT,
    parent_comment_id BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,
    create_time TIMESTAMP,
    modified_time TIMESTAMP
);
```
</details>