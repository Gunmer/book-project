import { Module, Provider } from '@nestjs/common';
import * as mongoose from 'mongoose';
import { BookProvider } from './schemas/book.schema';
import { BookRepository } from './repositories/book.repository';

const databaseProviders = [
  {
    provide: 'DATABASE_CONNECTION',
    useFactory: (): Promise<typeof mongoose> =>
      mongoose.connect('mongodb://localhost/test'),
  },
];

const REPOSITORIES: Provider[] = [
  BookRepository,
];

@Module({
  imports: [],
  providers: [
    ...REPOSITORIES,
    ...databaseProviders,
    ...BookProvider,
  ],
  exports: [
    ...REPOSITORIES,
  ],
})
export class DataModule {
}
