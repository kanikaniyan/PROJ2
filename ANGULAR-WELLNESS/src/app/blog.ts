import { Observable } from "rxjs";
import { BlogComments } from "./blog-comments";

export class Blog {
    blogId:number;
    blogTitle: string;
    blogContent: string;
    blogPosted: Date;
    status: string;
    noOfLikes: number;
    noOfComments: number;
    noOfViews: number;
    userId: number;
    username: string;
    blogComments: Observable<BlogComments[]>;
}
