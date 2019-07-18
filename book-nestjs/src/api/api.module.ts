import { DomainModule } from '../domain/domain.module';
import { Module } from '@nestjs/common';
import { BookController } from './controllers/book.controller';

@Module({
  imports: [DomainModule],
  controllers: [BookController],
  providers: [],
  exports: [],
})
export class ApiModule {
}
