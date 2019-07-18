import { Document } from 'mongoose';

export interface Book extends Document {
  title: string;
  authors: string[];
  categories: string[];
  isbn: string;
  pageCount: number;
  status: string;
  thumbnailUrl?: string;
  shortDescription?: string;
  longDescription?: string;
  publishedDate?: Date;
}
