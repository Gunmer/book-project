import * as mongoose from 'mongoose';
import { Connection } from 'mongoose';

export const BookSchema = new mongoose.Schema({
  title: { type: String, required: true },
  authors: { type: [String], required: true },
  categories: { type: [String], required: true },
  isbn: { type: String, required: true },
  pageCount: { type: String, required: true },
  status: { type: String, required: true },
  thumbnailUrl: { type: String, required: false },
  shortDescription: { type: String, required: false },
  longDescription: { type: String, required: false },
  publishedDate: { type: Date, required: false },
});

export const BookProvider = [{
  provide: 'BOOK_MODEL',
  useFactory: (connection: Connection) => connection.model('Book', BookSchema),
  inject: ['DATABASE_CONNECTION'],
}];
