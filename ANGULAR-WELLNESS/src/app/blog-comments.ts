import { Blog } from "./blog";

export class BlogComments {
    blogCommentId: number;
    userId: number;
    username: string;
    ProfileId: string;
    title: string;
    noOfLikes: number;
    blogComment: string;
    currentDate: Date;
    blog: Blog;
}
