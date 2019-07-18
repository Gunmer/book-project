import { Module, Provider } from '@nestjs/common';
import { FindBooksCommand } from './commans/find-books.command';
import { DataModule } from '../data/data.module';

const USE_CASES: Provider[] = [
  FindBooksCommand,
];

@Module({
  imports: [DataModule],
  providers: [
    ...USE_CASES,
  ],
  exports: [
    ...USE_CASES,
  ],
})
export class DomainModule {
}
